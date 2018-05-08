package edu.mccc.cos210.ds.demo.mvc;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.security.AlgorithmParameters;
import java.util.Comparator;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import edu.mccc.cos210.ds.Array;
import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IMap;
import edu.mccc.cos210.ds.ISet;
import edu.mccc.cos210.ds.Map;

public class Model1 implements IModel<String> {
	private IMap<String, BufferedImage> theMap = new Map<>();
	private Cipher cipher;
	public Model1() {
		initCipher();
		initData();
	}
	@Override
	public ISet<String> getNames() {
		return theMap.keySet();
	}
	@Override
	public BufferedImage getPhoto(String name) {
		return theMap.get(name);
	}
	@Override
	public Comparator<? super String> getComparator() {
		return (left, right) -> {
			int ln = left.indexOf(" ") + 1;
			int rn = right.indexOf(" ") + 1;
			return left.substring(ln).compareTo(right.substring(rn));
		};
	}
	public void initData() {
		try {
			FileInputStream fis = new FileInputStream("./data/yb.dat");
			CipherInputStream cis = new CipherInputStream(fis, cipher);
			BufferedImage yearBook = ImageIO.read(cis);
			IArray<BufferedImage> thePhotos = new Array<>(18);
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 6; col++) {
					AffineTransform at = AffineTransform.getTranslateInstance(-(17.0 / 2.0 + col * 184.0), -(13.0 / 2.0 + row * 241.0));
					BufferedImage pic = new BufferedImage(184, 241, BufferedImage.TYPE_INT_BGR);
					Graphics2D g2d = pic.createGraphics();
					g2d.drawRenderedImage(yearBook, at);
					g2d.dispose();
					thePhotos.set(row * 6 + col, pic);
				}
			}
			theMap.put("Sally Walderfin", thePhotos.get(0));
			theMap.put("Larabelle Coldfish", thePhotos.get(1));
			theMap.put("Daisy Tortre", thePhotos.get(2));
			theMap.put("Plunetta Judd", thePhotos.get(3));
			theMap.put("Osira Kuthmaker", thePhotos.get(4));
			theMap.put("Clara Leggato", thePhotos.get(5));			
			theMap.put("Fred Finkle", thePhotos.get(6));			
			theMap.put("Swetha Particula", thePhotos.get(7));			
			theMap.put("Mac Tortre", thePhotos.get(8));
			theMap.put("Tom Porter", thePhotos.get(9));
			theMap.put("Wilfrid Collato", thePhotos.get(10));			
			theMap.put("Jenni Wells", thePhotos.get(11));
			theMap.put("Rabbit Parker", thePhotos.get(12));			
			theMap.put("Sammy Chicago", thePhotos.get(13));			
			theMap.put("Molly Wartbottle", thePhotos.get(14));
			theMap.put("Lynn Smith", thePhotos.get(15));
			theMap.put("Bathye McClarin", thePhotos.get(16));
			theMap.put("Juju Hornfinkle", thePhotos.get(17));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	private void initCipher() {		
		try {
			SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKeySpec skeySpec = new SecretKeySpec("ABCDEFGHIJKLMNOPQRSTUVWX".getBytes(), "DESede");
			SecretKey skey = skeyFactory.generateSecret(skeySpec);
			AlgorithmParameters ap = AlgorithmParameters.getInstance("DESede");
			ap.init(new IvParameterSpec(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 }));
			this.cipher = Cipher.getInstance("DESede/CFB8/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey, ap);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
