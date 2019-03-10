package com.callidus.compliance.nipr.gw;

import javafx.util.Pair;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by ToZLa on 2019-03-10.
 */
public class ColorSpread {

    public static void main(String[] args) throws Exception {

        Pair maxColor = count(5, 4, colors1);

        System.out.println("Color with the biggest contiguous spread across a given grid is " + maxColor);

    }

    private static Pair<Color, Integer> count(int x, int y, Color[][] colors) throws Exception {

        if (colors == null || colors.length == 0) {
            throw new Exception("Empty Matrix!");
        }

        if (colors.length != y || colors[0].length != x) {
            throw new Exception("x & y needs to be the dimensions of the matrix!");
        }

        Pair<Color, Integer> maxColor = null;

        int i = 0;

        while (i < x || i < y) {

            if (i < y) {
                maxColor = findMax(i, x, colors, maxColor, true);
            }

            if (i < x) {
                maxColor = findMax(i, y, colors, maxColor, false);
            }

            i++;
        }

        return maxColor;
    }

    private static Pair<Color, Integer> findMax(int index, int length, Color[][] colors, Pair<Color, Integer> maxColor, boolean row) {

        Color previousColor = null;
        Color currentColor;

        int currentLength = 0;

        Pair<Color, Integer> max = maxColor;

        for (int i = 0; i < length; i++) {

            currentColor = row ? colors[index][i] : colors[i][index];

            currentLength = currentColor.equals(previousColor) ? ++currentLength : 1;

            if (max == null || currentLength > max.getValue()) {
                max = new Pair<>(currentColor, currentLength);
            }

            previousColor = currentColor;

        }

        return max;
    }

    @Test
    public void testThis() throws Exception {

        Pair<Color, Integer> maxColor;

        maxColor = count(5, 4, colors1);
        Assert.assertEquals(maxColor, new Pair<>(Color.BLUE, 3));

        maxColor = count(5, 1, colors2);
        Assert.assertEquals(maxColor, new Pair<>(Color.BLUE, 2));

        try {
            maxColor = count(5, 4, colors3);
            Assert.assertEquals(maxColor, null);
        } catch (Exception e) {
            Assert.assertEquals("Empty Matrix!", e.getMessage());
        }

        maxColor = count(5, 4, colors4);
        Assert.assertEquals(maxColor, new Pair<>(Color.GREEN, 3));

    }

    private enum Color {
        RED,
        GREEN,
        BLUE,
        YELLOW
    }

    private static Color[][] colors1 = new Color[][]{{
            Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED},
            {Color.GREEN, Color.RED, Color.BLUE, Color.RED, Color.YELLOW},
            {Color.GREEN, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE},
            {Color.GREEN, Color.GREEN, Color.RED, Color.YELLOW, Color.GREEN}};

    private static Color[][] colors2 = new Color[][]{{
            Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED}};

    private static Color[][] colors3 = new Color[][]{};

    private static Color[][] colors4 = new Color[][]{{
            Color.RED, Color.BLUE, Color.BLUE, Color.YELLOW, Color.RED},
            {Color.GREEN, Color.RED, Color.BLUE, Color.RED, Color.YELLOW},
            {Color.GREEN, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE},
            {Color.GREEN, Color.GREEN, Color.RED, Color.YELLOW, Color.GREEN}};

}
