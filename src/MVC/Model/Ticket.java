package MVC.Model;

public class Ticket {
    private String ticketNumber;
    private boolean unavailable;

    public Ticket(String ticketNumber){
        this.ticketNumber = ticketNumber;
        this.unavailable = false;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public boolean isUnavailable() {
        return unavailable;
    }

    public void setUnavailable(boolean unavailable) {
        this.unavailable = unavailable;
    }

    @Override
    public String toString() {
        return this.ticketNumber;
    }
}
