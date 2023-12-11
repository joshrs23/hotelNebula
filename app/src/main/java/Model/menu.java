package Model;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.hotelnebula.R;
import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    protected void setupBottomNavigationBar() {
        Button btnHome = findViewById(R.id.btn_home);
        Button btnReservations = findViewById(R.id.btn_reservations);
        Button btnProfile = findViewById(R.id.btn_profile);

        btnHome.setOnClickListener(v -> navigateToHome());
        btnReservations.setOnClickListener(v -> navigateToReservations());
        btnProfile.setOnClickListener(v -> navigateToProfile());
    }

    private void navigateToHome() {
        // Implementa la navegación al Home
    }

    private void navigateToReservations() {
        // Implementa la navegación a Reservations
    }

    private void navigateToProfile() {
        // Implementa la navegación al Profile
    }
}
