package zest;

import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.ForAll;

class ExcelSheetTest {
    @Test
    public void testExampleTitlesToNumbers() {
        // Test cases
        assertEquals(1L, ExcelSheet.titleToNumber("A"));
        assertEquals(28L, ExcelSheet.titleToNumber("AB"));
        assertEquals(701L, ExcelSheet.titleToNumber("ZY"));
        assertEquals(8353082582L, ExcelSheet.titleToNumber("ZZZZZZZ"));
    }

    @Test
    public void testInvalidChars() {
        // Test invalid title
        assertThrows(IllegalArgumentException.class, () -> {
            ExcelSheet.titleToNumber("A1");
        });
    }

    @Test
    public void testEmptyTitle() {
        // Test empty title
        assertThrows(IllegalArgumentException.class, () -> {
            ExcelSheet.titleToNumber("");
        });
    }

    @Test
    public void testTooLongTitle() {
        // Test title that is too long
        assertThrows(IllegalArgumentException.class, () -> {
            ExcelSheet.titleToNumber("ZZZZZZZZ");
        });
    }

    @Property
    public void testInRange(@ForAll @IntRange(min = 1, max = 7) int length) {
        String title = "";
        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('A' + Math.random() * 26);
            title = title + randomChar;
        }
        long result = ExcelSheet.titleToNumber(title);
        assertTrue(result >= 1 && result <= 8353082582L);
    }

}
