import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager {

    private List<Customer> customers;
    private List<Video> videos;

    public Manager() {
        customers = new ArrayList<Customer>();
        videos = new ArrayList<Video>();
    }

    void init() {
        Customer james = new Customer("James");
        Customer brown = new Customer("Brown");
        customers.add(james);
        customers.add(brown);

        Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date());
        Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date());
        videos.add(v1);
        videos.add(v2);

        Rental r1 = new Rental(v1);
        Rental r2 = new Rental(v2);

        james.addRental(r1);
        james.addRental(r2);
    }

    void clearRentals(String customerName) {
        Customer foundCustomer = findCustomer(customerName);

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + foundCustomer.getRentals().size());
            for (Rental rental : foundCustomer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }
            // Retail 을 Customer 도 가지고 있고... Video 도 가지고 있음..
            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }

    public void returnVideo(String customerName, String videoTitle) {
        Customer foundCustomer = findCustomer(customerName);
        if (foundCustomer == null) return;
        foundCustomer.returnVideo(videoTitle);
    }

    public Customer findCustomer(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }

    public String getCustomerReport(String customerName) {
        Customer foundCustomer = findCustomer(customerName);

        String result;
        if ( foundCustomer == null ) {
            result ="No customer found";
        } else {
            result = foundCustomer.getReport() ;
        }
        return result;
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public void addVideo(Video newVideo) {
        videos.add(newVideo);
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        VRUI ui = new VRUI(manager);
        ui.display();
    }

    public void rentVideo(Customer foundCustomer, String videoTitle) {
        Video foundVideo = null ;
        foundVideo = findVideo(videoTitle);

        if ( foundVideo == null ) return;

        Rental rental = new Rental(foundVideo) ;
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals() ;
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);
    }

    private Video findVideo(String videoTitle) {
        Video foundVideo = null;
        for ( Video video: getVideos() ) {
            if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
                foundVideo = video ;
                break ;
            }
        }
        return foundVideo;
    }
}
