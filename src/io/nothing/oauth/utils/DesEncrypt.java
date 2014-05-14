package io.nothing.oauth.utils;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import android.annotation.SuppressLint;
import android.util.Base64;

@SuppressLint("NewApi")
public class DesEncrypt {
    private String iv = "12345678";//Dummy iv (CHANGE IT!)
    private IvParameterSpec ivspec;

	private String password;
	private Key key;

	public DesEncrypt(String imei) {
		this.password = imei;
        ivspec = new IvParameterSpec(iv.getBytes());
	}

	protected void setKey(String strKey) {
		KeyGenerator _generator;
		try {
			_generator = KeyGenerator.getInstance("DES");
			SecureRandom sr = SecureRandom.getInstance( "SHA1PRNG", "Crypto" );
			sr.setSeed(strKey.getBytes());
			_generator.init(sr);
			KeySpec keySpec = new DESKeySpec(strKey.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key key = factory.generateSecret(keySpec);
			this.key = key;
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEncString(String strMing) {
		String strMi = "";
		try {
			return byte2hex(getEncCode(strMing.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strMi;
	}

	public String getDesString(String strMi) {
		String strMing = "";
		try {
			return new String(getDesCode(hex2byte(strMi.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strMing;
	}

	private void validateKey() {
		if (this.key == null)
			setKey(password);
	}

	private byte[] getEncCode(byte[] byteS) {
		Cipher cipher;
		byte[] byteFina = (byte[]) null;
		try {
			validateKey();
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(1, this.key, ivspec);
			byteFina = cipher.doFinal(Base64.encode(byteS, Base64.DEFAULT));
		} catch (Exception e) {
			e.printStackTrace();

			cipher = null;
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	private byte[] getDesCode(byte [] byteD) {
		Cipher cipher;
		byte[] byteFina = (byte[]) null;
		try {
			validateKey();
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(2, this.key,ivspec);
			byteFina = cipher.doFinal(Base64.decode(byteD, Base64.DEFAULT));
		} catch (Exception e) {
			e.printStackTrace();

			cipher = null;
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0)
			throw new IllegalArgumentException("length error");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);

			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

}
