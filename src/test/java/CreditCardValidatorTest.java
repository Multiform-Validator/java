import static io.github.multiform_validator.identity.CreditCardValidator.identifyFlagCard;
import static io.github.multiform_validator.identity.CreditCardValidator.isCreditCardValid;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class CreditCardValidatorTest {

  @Test
  void testIsCreditCardValid() {
    assertTrue(isCreditCardValid("4111111111111111"));
    assertTrue(isCreditCardValid("5500000000000004"));
    assertFalse(isCreditCardValid("1234567890123456"));
    assertFalse(isCreditCardValid("12345678901234567890"));
    assertThrows(IllegalArgumentException.class, () -> isCreditCardValid(null));
    assertThrows(IllegalArgumentException.class, () -> isCreditCardValid(""));
  }

  @Test
  void testIdentifyFlagCard() {
    HashMap<String, String> cards = new HashMap<>();

    cards.put("4111111111111111", "Visa");
    cards.put("5555555555554444", "Mastercard");
    cards.put("378282246310005", "American Express");
    cards.put("6011111111111117", "Discover");
    cards.put("3530111333300000", "JCB");
    cards.put("30569309025904", "Diners Club");
    cards.put("6304000000000000", "Maestro");
    cards.put("6200000000000005", "UnionPay");
    cards.put("6370950000000005", "Elo");
    cards.put("3841000000000000", "Hipercard");
    cards.put("1234567890123456", null);
    cards.put("", null);
    cards.put(null, null);

    for (Map.Entry<String, String> entry : cards.entrySet()) {
      Optional<String> card = identifyFlagCard(entry.getKey());
      if (entry.getKey() == null || entry.getValue() == null) {
        assertFalse(card.isPresent());
        continue;
      }

      if (card.isPresent()) {
        assertEquals(entry.getValue(), card.get());
      } else {
        fail("Card is not present");
      }
    }
  }
}
