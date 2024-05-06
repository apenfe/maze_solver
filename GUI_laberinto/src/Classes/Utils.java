package Classes;

/**
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.*;

public class Utils{
	
	private static final String EU_FORMAT = "dd/MM/yyyy";
	private static final String SQL_FORMAT = "yyyy-MM-dd";
	
	public static int getAge(String birthdate) {
		
		birthdate = formatDateEU(birthdate);
		
		if(validateDate(birthdate)) {
			
			String[] data = birthdate.split("/");
			int year = Integer.parseInt(data[2]);
			int month = Integer.parseInt(data[1]);
			int day = Integer.parseInt(data[0]);
			
			Date date = new Date(System.currentTimeMillis());
			String today = new SimpleDateFormat(EU_FORMAT).format(date);
			
			String[] todayData = today.split("/");
			int actualYear = Integer.parseInt(todayData[2]);
			int actualMonth = Integer.parseInt(todayData[1]);
			int actualDay = Integer.parseInt(todayData[0]);
			
			int years = actualYear-year;
			
			if (actualMonth < month || (actualMonth == month && actualDay < day)) {
				
                years--;
                
            }
			
			return years;
		}
		
		return -1;
		
	}

	public static String formatDateSQL(String date) {

		DateFormat sqlFormat = new SimpleDateFormat(SQL_FORMAT);
		DateFormat euFormat = new SimpleDateFormat(EU_FORMAT);

		try {

			Date sqlDate = euFormat.parse(date);
			return sqlFormat.format(sqlDate);

		} catch (ParseException e) {

			e.printStackTrace();
			return date;

		}

	}
	
	public static String formatDateEU(String sqlDate) {
		
        DateFormat sqlFormat = new SimpleDateFormat(SQL_FORMAT);
        DateFormat euFormat = new SimpleDateFormat(EU_FORMAT);
        
        try {
            
            Date date = sqlFormat.parse(sqlDate);
            return euFormat.format(date);
            
        } catch (ParseException e) {
            
            e.printStackTrace();
            return sqlDate;
            
        }
        
	}
	
	public static String encryptMd5(String password) {
		
		try {
			
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : digest) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
        	
            e.printStackTrace();
            
        }
		
		return password;
		
	}
	
	public static boolean validateEmail(String email) {
		
		return email.matches("^[\\w-\\+]+(\\.[\\w-\\+]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	}
	
	public static boolean validateNif(String nif) {
		
		return nif.matches("^\\d{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E|t|r|w|a|g|m|y|f|p|d|x|b|n|j|z|s|q|v|h|l|c|k|e]$");
		
	}
	
	public static boolean validateDate(String date) {
        
        if(!date.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$")) {
        	
        	return false;
        	
        }
        
        String[] data = date.split("/");
        int day = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int year = Integer.parseInt(data[2]);
        
        boolean bisiesto = false;

		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			bisiesto = true;
		}

		if (month >= 1 && month <= 12) {

			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				return day <= 31;	

			} else if (month == 4 || month == 6 || month == 9 || month == 11) {

				return day <= 30;

			} else {

				return (day <= 29 && bisiesto)||(day<=28 && !bisiesto);	

			}

		}
		
		return false;
	}
	
	public static boolean validateName(String name) {
		
		return name.matches("^([a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÜÈÌÙÒñÑ][a-záéíóúàèìòùñüç']+)( [a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÙÜÒñÑ]{1}[a-záéíóúàèìòùñüç']+(-[a-záéíóúàèìòùñüç']+)?){1,2}$");
		
	}
	
	public static boolean validateUserName(String username) {
		
		return username.matches("^@[\\w-]+$");
		
	}
	
	public static boolean validatePassword(String password) {
		
		return password.matches("(?=.*[A-ZÑ])(?=.*[a-zñ])(?=.*\\d)(?=.+[$*-+&!¡#?%]).{8,}");
	
	}
	
}