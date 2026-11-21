
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
    
    // All-DU-Pairs: gcd-ийн давталтын дараах x, y
    @Test
    void gcdMultipleIterationsDU() {
        assertEquals(6, DataFlowTargets.gcd(48, 18));
    }

    // All-DU-Pairs: gcd-ийн давталтгүй зам
    @Test
    void gcdNoIterationDU() {
        assertEquals(9, DataFlowTargets.gcd(9, 0));
    }

    // All-DU-Pairs: streak-ийн 46 -> 41 зам
    @Test
    void bonusResetThenPresentDU() {
        assertEquals(0, DataFlowTargets.bonus(
            new int[]{0, 1}, 2));
    }

    // All-DU-Pairs: reset-ийн дараа давталт дуусах
    @Test
    void bonusEndsWithAbsenceDU() {
        assertEquals(0, DataFlowTargets.bonus(
            new int[]{1, 0}, 2));
    }

    // All-DU-Pairs: score-ийн 49 -> 54 -> 56 зам
    @Test
    void bonusStreakOnlyDU() {
        assertEquals(3, DataFlowTargets.bonus(
            new int[]{1, 1, 1, 1, 1}, 6));
    }

    // All-DU-Pairs: score-ийн 51 -> 56 зам
    @Test
    void bonusThresholdOnlyDU() {
        assertEquals(5, DataFlowTargets.bonus(
            new int[]{1, 0, 1}, 2));
    }

}
