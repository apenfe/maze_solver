package Classes;

public class AdminOptions{
	
	public static Session adminSession = new Session();
	public static User me = adminSession.currentUser;
	
	public static void menu() {
		
		System.out.println(Config.ADMIN);
	
		int option = -1;
		
		do {
			
			option = Input.getInt(Config.ADMIN_USER_DATA, false);
			
			if(option == 0) {
				
				if(Utils.confirmExit()) {
					break;
				}else {
					option = -1;
				}
				
			}else if(option == 1) {
				
				adminSession.signup();
					
			}else if(option == 2) {
				
				listado(1);
	
			}else if(option == 3) {
				
				listado(2);
				
			}else if(option == 4) {
				
				listado(3);
				
			}
			
		}while(true);
				
	}
	
	private static void listado(int option) {
		
		User[] users = adminSession.db.listUsers();
		
		do {
		
			System.out.println("\n\t\tListado de usuarios: \n");
			
			for (int i = 0; i < users.length; i++) {
				
				System.out.println("\t\t["+(i+1)+"] ---> "+users[i].username);
				
			}
			
			System.out.println("\t\t[0] ---> SALIR\n");
			int user = Input.getInt(Config.YELLOW+"\t\tSeleccione una opción entre 1 y "+users.length+" para ver mas detalles O pulse 0 para salir: "+Config.RESET, false);
			
			if(user ==0) {
				
				break;
				
			}else if(user>0 && user<=users.length) {
				
				if(option==1) {
					
					users[user-1].info();
					
				}else if(option==2) {
					
					System.out.println(Config.ADMIN);
					adminSession.currentUser=users[user-1];
					ChangeData.changeUserDataAdmin(adminSession);
					adminSession.currentUser=me;
					
				}else if(option==3) {
					
					System.out.println(Config.ADMIN);
					adminSession.currentUser=users[user-1];
					ChangeData.deleteUserAdmin(adminSession);
					adminSession.currentUser=me;
					
				}
				
				break;
				
			}else {
				
				System.out.println("Seleccione una opcion valida entre 0 y "+users.length+".\n");
				
			}
		
		}while(true);
		
	}
	
}