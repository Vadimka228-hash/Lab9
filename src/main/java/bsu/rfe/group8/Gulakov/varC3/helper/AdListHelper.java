package bsu.rfe.group8.Gulakov.varC3.helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import bsu.rfe.group8.Gulakov.varC3.entity.AdList;
import jakarta.servlet.ServletContext;

public abstract class AdListHelper {
	private static final String ADS_FILENAME = "WEB-INF/ads.dat";
	private static String ADS_PATH = null;

	public static AdList readAdList(ServletContext context) {
		try {
			ADS_PATH = context.getRealPath(ADS_FILENAME);
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(ADS_PATH));
			return (AdList)in.readObject();
		} catch (Exception e) {
			return new AdList();
		}
	}

	public static void saveAdList(AdList ads) {
		synchronized (ads) {
			try {
				@SuppressWarnings("resource")
				ObjectOutputStream out = new ObjectOutputStream(new
						FileOutputStream(ADS_PATH));
				out.writeObject(ads);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}