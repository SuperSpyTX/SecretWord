package me.freebuild.superspytx.secretword.database;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lib.PatPeter.SQLibrary.SQLite;
import me.freebuild.superspytx.secretword.Core;
import me.freebuild.superspytx.secretword.settings.Settings;
import me.freebuild.superspytx.secretword.utility.DatabaseUtils;

public class DatabaseCore {

	public Core core = null;
	private SQLite handler = null;
	public Map<String, SecretPlayer> secplayers = new HashMap<String, SecretPlayer>();

	public DatabaseCore(Core instance) {
		core = instance;

		// connect database
		handler = new SQLite(Settings.log, Settings.logPrefix, "credentials",
				instance.getDataFolder().getPath());
		handler.open();

		// now add tables if it doesn't exist.
		if (!handler.checkTable("secretword")) {
			handler.createTable("CREATE TABLE secretword (id INT AUTO_INCREMENT PRIMARY_KEY, mcuser VARCHAR(16), word VARCHAR(32), salt VARCHAR(255), ip VARCHAR(25), lastlogin VARCHAR(255));");
		}

	}

	public void addLogin(String mcuser, String pass) {
		byte[] salt = DatabaseUtils.getSalt();
		String saltDb = DatabaseUtils.byteToBase64(salt);
		String password = null;
		try {
			password = DatabaseUtils.hashPassword(pass, salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (password != null) {
			// add to database.
			/*
			 * handler.query(
			 * "INSERT INTO secretword (mcuser, word, salt, lastlogin) VALUES ('"
			 * + mcuser + "', '" + password + "', '" + salt + "', '" +
			 * Long.toString(System.currentTimeMillis()) + "');");
			 */
			try {
				PreparedStatement ps = handler
						.prepare("INSERT INTO secretword (mcuser, word, salt, lastlogin) VALUES (?,?,?,?)");
				ps.setString(0 + 1, mcuser);
				ps.setString(1 + 1, password);
				ps.setString(2 + 1, saltDb);
				ps.setString(3 + 1, Long.toString(System.currentTimeMillis()));
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public boolean ipMatches(String ip, String user) {
		if (!userExists(user)) {
			return false;
		}
		
		PreparedStatement ps = handler.prepare("SELECT ip FROM secretword WHERE `mcuser` = ?");
		try {
			ps.setString(0 + 1, user);
			ps.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ResultSet query = null;
		try {
			query = ps.getResultSet();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (query != null) {
			String dIP = null;
			try {
				if (query.next()) {
					dIP = query.getString("ip");
				}

				if (dIP.equalsIgnoreCase(ip)) {
					return true;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean checkLogin(String mcuser, String pass) {
		PreparedStatement ps = handler.prepare("SELECT word,salt FROM secretword WHERE `mcuser` = ?");
		try {    
			ps.setString(0 + 1, mcuser);
			ps.execute();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ResultSet query = null;
		try {
			query = ps.getResultSet();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			if (query != null && query.next()) {
				String dbWord;
				try {
					dbWord = query.getString("word");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				String dbSalt;
				try {
					dbSalt = query.getString("salt");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

				try {
					byte[] dWord = DatabaseUtils.base64ToByte(dbWord);
					byte[] dSalt = DatabaseUtils.base64ToByte(dbSalt);
					byte[] proposedHash = DatabaseUtils.getHash(1000, pass,
							dSalt);

					if (Arrays.equals(proposedHash, dWord)) {
						return true;
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean userExists(String user) {
		PreparedStatement ps = handler.prepare("SELECT mcuser FROM secretword WHERE `mcuser` = ?");
		try {    
			ps.setString(0 + 1, user);
			ps.execute();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ResultSet query = null;
		try {
			query = ps.getResultSet();
			if (query != null)
				if(query.next())
					if (query.getString("mcuser") != null)
						return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		return false;
	}
}
