package Model;

import androidx.annotation.NonNull;

public class Reservations {
    private int UserId;
    private int RoomdId;
    private String ReservationDate;
    private String ExpectedCheckout;

    public Reservations(int userId, int roomdId, String reservationDate, String expectedCheckout) {
        UserId = userId;
        RoomdId = roomdId;
        ReservationDate = reservationDate;
        ExpectedCheckout = expectedCheckout;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getRoomdId() {
        return RoomdId;
    }

    public void setRoomdId(int roomdId) {
        RoomdId = roomdId;
    }

    public String getReservationDate() {
        return ReservationDate;
    }

    public void setReservationDate(String reservationDate) {
        ReservationDate = reservationDate;
    }

    public String getExpectedCheckout() {
        return ExpectedCheckout;
    }

    public void setExpectedCheckout(String expectedCheckout) {
        ExpectedCheckout = expectedCheckout;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
