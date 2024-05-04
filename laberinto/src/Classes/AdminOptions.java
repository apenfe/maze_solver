package Classes;

public class AdminOptions{

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
				
				/*
				 * 1. Crear nuevos usuarios: El administrador podrá crear un nuevo usuario en un
	proceso idéntico al registro.
				 */
				
				
					
			}else if(option == 2) {
				
				/*
				 * 2. Ver usuarios: El administrador podrá listar todos los usuarios y seleccionar uno para
	ver su información en detalle.
				 */
					
			}else if(option == 3) {
				
				/*3. Modificar usuarios: Partiendo del listado de usuarios y sus detalles, se puede
				habilitar una opción para modificar los datos de un usuario en concreto.
				
					*/
			}else if(option == 4) {
				/*
				 * 4. Eliminar usuarios: Partiendo del listado de usuarios y sus detalles, se puede habilitar
	una opción para eliminar un usuario en concreto.
				 */
				
					
			}
			
		}while(true);
				
	}
	
}