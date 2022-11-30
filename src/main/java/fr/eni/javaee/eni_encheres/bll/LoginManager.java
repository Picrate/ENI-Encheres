/**
 * Gestionnaire d'authentification utilisateur
 */
package fr.eni.javaee.eni_encheres.bll;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;

/**
 * Classe de gestion de l'authentification d'un utilisateur.
 * 
 * @author patrice
 *
 */
public class LoginManager {

	private static LoginManager instance = null;

	private static SecretKey secretKey;
	private static byte[] masterKey = null;
	private static byte[] seed = null;
	private static byte[] savedParams = null;
	private static byte[] salt = new byte[256];
	private static Cipher pbeCipher;
	private static SecureRandom rnd;
	private static SecretKeyFactory secretKeyFactory;
	AlgorithmParameters algParams;

	private LoginManager() {

		try {
			salt = new byte[2048];
			masterKey = CryptoDatasManager.getInstance().getMasterKey();
			seed = decodeBase64(CryptoDatasManager.getInstance().getSeed());
			savedParams = decodeBase64(CryptoDatasManager.getInstance().getbase64Params());
			rnd = new SecureRandom();
			rnd.nextBytes(salt);
			new PBEParameterSpec(salt, 1000);
			PBEKeySpec pks = new PBEKeySpec(bytesToChars(masterKey), salt, 1000);
			algParams = AlgorithmParameters.getInstance("PBEWithHmacSHA256AndAES_256");
			algParams.init(savedParams);
			secretKeyFactory = SecretKeyFactory.getInstance("PBEWithHmacSHA256AndAES_256");
			secretKey = secretKeyFactory.generateSecret(pks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		String userStoredPassword = UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(username)
				.getPassword();
		// Si la chaine n'est pas null
		if (Objects.nonNull(userStoredPassword)) {
			// On decode la chain de caractere en BASE64
			byte[] binaryPassword = decodeBase64(userStoredPassword);
			// On décrypte la chaine.
			char[] charateredPassword = this.decryptPassword(binaryPassword);
			// Si les Mot de passes correspondent authenticate = true
			if (this.checkPasswordMatch(password, charateredPassword)) {
				authenticate = true;
			}
		} else {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.REQUESTED_USER_IS_NULL);
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
	private byte[] encryptPassword(char[] password) throws BusinessException {
		byte[] bytesPassword = charsToBytes(password);
		byte[] ciphertext = null;
		try {
			pbeCipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_256");
			pbeCipher.init(Cipher.ENCRYPT_MODE, secretKey, algParams);
			ciphertext = pbeCipher.doFinal(bytesPassword);

		} catch (Exception e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.CRYPT_ERROR);
			e.printStackTrace();
		}
		return ciphertext;
	}

	/**
	 * Décrypte le mot de passe
	 * @param password le mot de passe à décrypter
	 * @return le mot de passe en clair.
	 */
	private char[] decryptPassword(byte[] password) throws BusinessException{
		byte[] ciphertext = null;
		try {
			pbeCipher = Cipher.getInstance("PBEWithHmacSHA256AndAES_256");
			pbeCipher.init(Cipher.DECRYPT_MODE, secretKey, algParams);
			ciphertext = pbeCipher.doFinal(password);
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.DECRYPT_ERROR);
			e.printStackTrace();
		}

		return bytesToChars(ciphertext);
	}
	
	/**
	 * Crypte le mot de passse et le retourne sous forme de chaine de caracteres ecodé en BASE64
	 * @param password le mot de passe à crypter
	 * @return La chaine de caractere représentant le mot de passe crypté encodé en BASE64
	 * @throws BusinessException
	 */
	
	public String getBase64Password(char[] password) throws BusinessException{
		try {
			return encodeBase64(this.encryptPassword(password));
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
