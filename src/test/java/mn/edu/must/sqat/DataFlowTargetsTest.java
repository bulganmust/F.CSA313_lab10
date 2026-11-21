
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.*;

class DataFlowTargetsTest {

    @Tag("alldefs")
    @Test
    void gcdNormal() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Tag("alldefs")
    @Test
    void gcdSecondZero() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    void gcdFirstZero() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }

    @Test
    void gcdEqualNumbers() {
        assertEquals(6, DataFlowTargets.gcd(6, 6));
    }

    @Test
    void bonusNormal() {
        assertEquals(5, DataFlowTargets.bonus(
            new int[]{1, 0, 1, 1}, 3));
    }

    @Tag("alldefs")
    @Test
    void bonusWithStreak() {
        assertEquals(8, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1, 1}, 5));
    }

    @Test
    void bonusNoReward() {
        assertEquals(0, DataFlowTargets.bonus(
            new int[]{0, 0, 1}, 2));
    }

    @Test
    void bonusEmpty() {
        assertEquals(0, DataFlowTargets.bonus(
            new int[]{}, 1));
    }

    @Tag("alldefs")
    @Test
    void bonusNull() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 2));
    }

    @Tag("alldefs")
    @Test
    void bonusNegativeThreshold() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(
                new int[]{1, 1}, -1));
    }

    @Test
    void bonusZeroThresholdEmptyAttendance() {
        assertEquals(5, DataFlowTargets.bonus(
            new int[]{}, 0));
    }

    @Tag("alldefs")
    @Test
    void bonusStreakResetsAndRestarts() {
        assertEquals(8, DataFlowTargets.bonus(
            new int[]{1, 1, 0, 1, 1, 1, 1, 1}, 7));
    }

    @Test
    void bonusStreakWithoutThresholdReward() {
        assertEquals(3, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1, 1}, 6));
    }

    @Test
    void bonusStreakExactlyFour() {
        assertEquals(5, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1}, 4));
    }
}
