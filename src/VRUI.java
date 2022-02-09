import java.util.Date;
import java.util.Scanner;

public class VRUI {
	private static Scanner scanner = new Scanner(System.in) ;
	private Manager manager;

	public VRUI(Manager manager){
		this.manager= manager;
	}

	public void display() {
		boolean quit = false ;
		while ( ! quit ) {
			int command = showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: listCustomers() ; break ;
				case 2: listVideos() ; break ;
				case 3: register("customer") ; break ;
				case 4: register("video") ; break ;
				case 5: rentVideo() ; break ;
				case 6: returnVideo() ; break ;
				case 7: getCustomerReport() ; break;
				case 8: clearRentals() ; break ;
				case -1: init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void init(){
		manager.init();
	}

	public void clearRentals() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;
		manager.clearRentals(customerName);
	}

	public void returnVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		manager.returnVideo(customerName, videoTitle);
	}

	public void listVideos() {
		System.out.println("List of videos");

		for ( Video video: manager.getVideos()) {
			System.out.println("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
		System.out.println("End of list");
	}

	public void listCustomers() {
		System.out.println("List of customers");
		for ( Customer customer: manager.getCustomers() ) {
			System.out.println("Name: " + customer.getName() +
					"\tRentals: " + customer.getRentals().size()) ;
			for ( Rental rental: customer.getRentals() ) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}
		}
		System.out.println("End of list");
	}

	public void getCustomerReport() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		System.out.println(manager.getCustomerReport(customerName));
	}

	public void rentVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = manager.findCustomer(customerName);
		if ( foundCustomer == null ) return ;

		System.out.println("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		manager.rentVideo(foundCustomer, videoTitle);
	}

	public void register(String object) {
		if ( object.equals("customer") ) {
			System.out.println("Enter customer name: ") ;
			String name = scanner.next();
			Customer customer = new Customer(name) ;
			manager.addCustomer(customer); ;
		}
		else {
			System.out.println("Enter video title to register: ") ;
			String title = scanner.next() ;

			System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
			int videoType = scanner.nextInt();

			System.out.println("Enter price code( 1 for Regular, 2 for New Release ):") ;
			int priceCode = scanner.nextInt();

			Date registeredDate = new Date();
			Video video = new Video(title, videoType, priceCode, registeredDate) ;
			manager.addVideo(video) ;
		}
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;
	}
}
