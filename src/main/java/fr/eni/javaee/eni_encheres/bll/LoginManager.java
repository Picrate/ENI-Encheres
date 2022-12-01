/**
 * Gestionnaire d'authentification utilisateur
 */
package fr.eni.javaee.eni_encheres.bll;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;

/**
 * Classe de gestion de l'authentification d'un utilisateur.
 * 
 * @author patrice
 *
 */
public class LoginManager {

	private final static String CRYPT_ALGORITHM = "PBEWithHmacSHA256AndAES_256";
	public final static String PASSWORD_KEY = "password";
	public final static String PARAMS_KEY = "params";
	
	private static LoginManager instance = null;

	private static SecretKey secretKey;
	private static byte[] masterKey = null;
	private static byte[] salt;
	private static Cipher pbeCipher;
	private static SecureRandom rnd;
	private static SecretKeyFactory secretKeyFactory;
	AlgorithmParameters algParams;
	

	private LoginManager() {
		
		try {
			salt = new byte[2048];
			masterKey = CryptoDatasManager.getInstance().getMasterKey();
			rnd = new SecureRandom();
			rnd.nextBytes(salt);
			PBEKeySpec pks = new PBEKeySpec(bytesToChars(masterKey), salt, 1000);
			secretKeyFactory = SecretKeyFactory.getInstance(CRYPT_ALGORITHM);
			secretKey = secretKeyFactory.generateSecret(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Singleton LoginManager
	 * @return le singleton.
	 */
	public static LoginManager getInstance() {
		if (instance == null) {
			instance = new LoginManager();
		} else {
			rnd.nextBytes(salt);
		}
		return instance;
	}

	/**
	 * Authentifie un compte en fonction de l'email ou du pseudo
	 * 
	 * @param username l'email ou le login de l'utilsateur
	 * @param password le mot de passe fournit dans le formulaire d'authentification
	 * @return true si le mot de passe correspond / false sinon
	 * @throws BusinessException
	 */
	public boolean authenticateUser(String username, char[] password) throws BusinessException {
		boolean authenticate = false;

		if (username == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_OR_EMAIL_NULL);
			throw businessException;
		} else if (password.length == 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.PASSWORD_IS_NULL);
			throw businessException;
		}
		// On récupère le mot de passe stocké en base
		try {
		
		Utilisateur user = UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(username);
		String userStoredPassword = user.getPassword();
		// Si la chaine n'est pas null
		if (Objects.nonNull(userStoredPassword)) {
			// On decode la chain de caractere en BASE64
			byte[] binaryPassword = decodeBase64(userStoredPassword);
			// On récupère les paramètres de cryptage utilisé ultérieurement
			String base64UserAlgParams = this.getLoginParameters(user.getNo_utilisateur());
			// On décrypte lle mot de passe en fournissant les parametres.
			char[] charateredPassword = this.decryptPassword(binaryPassword, decodeBase64(base64UserAlgParams) );
			// Si les Mot de passes correspondent authenticate = true
			if (this.checkPasswordMatch(password, charateredPassword)) {
				authenticate = true;
			}
		} else {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.REQUESTED_USER_IS_NULL);
			throw be;
		}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.CRYPT_ERROR);
			throw be;
		}

		return authenticate;
	}

	/**
	 * Vérification de l'existence de l'email sur un compte utilisateur
	 * 
	 * @param email l'email à vérifier
	 * @return true si il existe / false sinon
	 */
	public boolean checkEmailExists(String email) throws BusinessException {
		boolean checkStatus = true;
		Utilisateur utilisateur = UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(email);
		if (Objects.isNull(utilisateur)) {
			checkStatus = false;
		}
		return checkStatus;
	}

	/**
	 * Vérification de l'existence du pseudo sur un compte utilisateur
	 * 
	 * @param pseudo le pseudo à tester
	 * @return true si il existe / false sinon
	 * @throws BusinessException
	 */
	public boolean checkPseudoExists(String pseudo) throws BusinessException {
		boolean checkStatus = true;
		Utilisateur utilisateur = UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(pseudo);
		if (Objects.isNull(utilisateur)) {
			checkStatus = false;
		}
		return checkStatus;
	}

	/**
	 * Vérifie si les mots de passe correspondent
	 * 
	 * @param password  mot passe.
	 * @param cPassword la confirmation du mot de passe.
	 * @return true si les mots de passe correspondent / false sinon.
	 */
	public boolean checkPasswordMatch(char[] password, char[] cPassword) {
		boolean checkStatus = false;
		if (password.length == cPassword.length) {
			for (int i = 0; i < password.length; i++) {
				char cp = password[i];
				char ccp = cPassword[i];
				if (Character.compare(cp, ccp) != 0) {
					checkStatus = false;
					break;
				} else {
					checkStatus = true;
				}
			}
		}

		return checkStatus;
	}

	/**
	 * Crypte le mot de passe
	 * @param password le mot de passe à crypter
	 * @return le mot de passe crypté.
	 */
	private Map<String,byte[]> encryptPassword(char[] password) throws BusinessException {
		byte[] bytesPassword = charsToBytes(password);
		byte[] ciphertext = null;
		byte[] encodedParams;
		Map<String, byte[]> result = new HashMap<>(2);
		try {
			pbeCipher = Cipher.getInstance(CRYPT_ALGORITHM);
			pbeCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			ciphertext = pbeCipher.doFinal(bytesPassword);
			AlgorithmParameters params = pbeCipher.getParameters();
			encodedParams = params.getEncoded();
			result.put(PASSWORD_KEY, ciphertext);
			result.put(PARAMS_KEY, encodedParams);
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.CRYPT_ERROR);
			e.printStackTrace();
			throw be;
		}
		return result;
	}

	/**
	 * Décrypte le mot de passe
	 * @param password le mot de passe à décrypter
	 * @return le mot de passe en clair.
	 */
	private char[] decryptPassword(byte[] password, byte[] algUserParams) throws BusinessException{
		byte[] ciphertext = null;
		try {
			AlgorithmParameters params = AlgorithmParameters.getInstance(CRYPT_ALGORITHM);
			params.init(algUserParams);
			pbeCipher = Cipher.getInstance(CRYPT_ALGORITHM);
			pbeCipher.init(Cipher.DECRYPT_MODE, secretKey, params);
			ciphertext = pbeCipher.doFinal(password);
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.DECRYPT_ERROR);
			e.printStackTrace();
			throw be;
		}
		return bytesToChars(ciphertext);
	}
	
	private String getLoginParameters(int userId) throws BusinessException {
		
		String userLoginParams = DAOFactory.getUserParametersDAO().selectElementById(userId).loginParameters;
		if (Objects.isNull(userLoginParams)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.DECRYPT_ERROR);
			be.printStackTrace();
			throw be;
		} else {
			return userLoginParams;
		}
	}
	
	/**
	 * Crypte le mot de passse et le retourne sous forme de chaine de caracteres ecodé en BASE64
	 * @param password le mot de passe à crypter
	 * @return La chaine de caractere représentant le mot de passe crypté encodé en BASE64
	 * @throws BusinessException
	 */
	
	public Map<String, String> getBase64Password(char[] password) throws BusinessException{
		
		
		
		try {
			Map<String, byte[]> cryptedResult = new HashMap<>(2);
			Map<String, String> base64Result = new HashMap<>(2);
			cryptedResult = this.encryptPassword(password);
			String base64Password = encodeBase64(cryptedResult.get(PASSWORD_KEY));
			String base64Params = encodeBase64(cryptedResult.get(PARAMS_KEY));
			base64Result.put(PASSWORD_KEY, base64Password);
			base64Result.put(PARAMS_KEY, base64Params);
			return base64Result;
		} catch (BusinessException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.CRYPT_ERROR);
			e.printStackTrace();
			throw be;
		}
	}

	/**
	 * Decode une chaine de caractere encodée en BASE64
	 * 
	 * @param base64String la chaine à décoder
	 * @return le tableau d'octets correspondant.
	 */
	private byte[] decodeBase64(String base64String) {
		return Base64.getDecoder().decode(base64String);
	}

	/**
	 * Encode un tableau d'octet en chaine de caratere BASE64
	 * 
	 * @param byteArray le tableau à encoder
	 * @return la chaine en BASE64
	 */
	private String encodeBase64(byte[] byteArray) {
		return Base64.getEncoder().encodeToString(byteArray);
	}

	/**
	 * Transforme un tableau de caracteres en tableau d'octets
	 * 
	 * @param bytes le tablerau d'octets à transformer
	 * @return le tableau de caractere correspondant.
	 */
	private char[] bytesToChars(byte[] bytes) {
		char[] chars = new char[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			char c = (char) bytes[i];
			chars[i] = c;
		}

		return chars;
	}

	/**
	 * Transforme un tableau de caractere en tableau d'octet
	 * 
	 * @param chars le tableau de caractere à transcoder.
	 * @return le tableau d'octets correspondants.
	 */
	private byte[] charsToBytes(char[] chars) {
		byte[] bytes = new byte[chars.length];
		for (int i = 0; i < chars.length; i++) {
			byte b = (byte) chars[i];
			bytes[i] = b;
		}
		return bytes;
	}

}
