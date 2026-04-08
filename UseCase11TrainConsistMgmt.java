import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase11TrainConsistMgmt {

    static final String TRAIN_REGEX = "TRN-\\d{4}";
    static final String CARGO_REGEX = "PET-[A-Z]{2}";

    public static boolean isValidTrainId(String trainId) {
        return Pattern.matches(TRAIN_REGEX, trainId);
    }

    public static boolean isValidCargoCode(String cargoCode) {
        return Pattern.matches(CARGO_REGEX, cargoCode);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();

        boolean trainValid = isValidTrainId(trainId);
        boolean cargoValid = isValidCargoCode(cargoCode);

        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: " + trainValid);
        System.out.println("Cargo Code Valid: " + cargoValid);

        System.out.println("\nUC11 validation completed...");
    }

    @Test
    void testRegex_ValidTrainID() {
        assertTrue(isValidTrainId("TRN-1234"));
    }

    @Test
    void testRegex_InvalidTrainIDFormat() {
        assertFalse(isValidTrainId("TRAIN12"));
        assertFalse(isValidTrainId("TRN12A"));
        assertFalse(isValidTrainId("1234-TRN"));
    }

    @Test
    void testRegex_ValidCargoCode() {
        assertTrue(isValidCargoCode("PET-AB"));
    }

    @Test
    void testRegex_InvalidCargoCodeFormat() {
        assertFalse(isValidCargoCode("PET-ab"));
        assertFalse(isValidCargoCode("PET123"));
        assertFalse(isValidCargoCode("AB-PET"));
    }

    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(isValidTrainId("TRN-123"));
        assertFalse(isValidTrainId("TRN-12345"));
    }

    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(isValidCargoCode("PET-Ab"));
    }

    @Test
    void testRegex_EmptyInputHandling() {
        assertFalse(isValidTrainId(""));
        assertFalse(isValidCargoCode(""));
    }

    @Test
    void testRegex_ExactPatternMatch() {
        assertFalse(isValidTrainId("TRN-1234X"));
        assertFalse(isValidCargoCode("PET-ABC"));
    }
}