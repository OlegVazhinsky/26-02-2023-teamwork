public class Wave {
    private static final int valueStart = -1;
    private static final int valueEnd = -2;
    private static final int valueClear = 0;

    public static void main(String[] args) { // TODO удалить (нужно только для теста)
        var map = new Map();
        var start = new Point(0, 1);
        var end = new Point(8, 7);

        Wave.setWave(map, start); // тест на моках
        // Wave.findWay(map, start, end); // тест на моках

        map.showMap();
    }

    public static void setWave(Map map, Point start) {
        // TODO заменить map.data на переменную данных карты (int[][])
        map.data[start.y][start.x] = valueStart;
        search(map.data, start.x, start.y, 0, false);
    }

    public static void findWay(Map map, Point start, Point end) {
        // TODO заменить map.data на переменную данных карты (int[][])
        map.data[start.y][start.x] = valueStart;
        map.data[end.y][end.x] = valueEnd;
        search(map.data, start.x, start.y, 0, true);
    }

    private static boolean search(int[][] map, int x, int y, int step, boolean onlyWay) {
        boolean isWay = false;
        int[][] check = {{x, y - 1}, {x + 1, y}, {x, y + 1}, {x - 1, y}};
        if (map[y][x] == valueClear) map[y][x] = step;
        int data;
        for (int[] p : check) {
            try {
                data = map[p[1]][p[0]];
                if (data == valueClear) isWay = isWay || search(map, p[0], p[1], step + 1, onlyWay);
                if (data == valueEnd && onlyWay) isWay = true;
            } catch (Exception e) {
                //
            }
        }
        if (!isWay && onlyWay) map[y][x] = valueClear;
        return isWay;
    }
}

// TODO удалить все ниже (нужно только для теста)
class Map {
    public int[][] data = {
            {-9, -9, -9, -9, -9, -9, -9, -9, -9},
            {-9, 00, -9, 00, 00, 00, 00, 00, -9},
            {-9, 00, -9, 00, -9, -9, -9, -9, -9},
            {-9, 00, 00, 00, 00, 00, 00, 00, -9},
            {-9, -9, -9, -9, -9, -9, -9, 00, -9},
            {-9, 00, 00, 00, 00, 00, 00, 00, -9},
            {-9, 00, -9, 00, -9, -9, -9, -9, -9},
            {-9, -9, -9, 00, -9, 00, -9, 00, -9},
            {-9, 00, -9, 00, 00, 00, -9, 00, -9},
            {-9, 00, 00, 00, -9, 00, 00, 00, -9},
            {-9, -9, -9, -9, -9, -9, -9, -9, -9},
    };

    public void showMap() {
        var response = new StringBuilder();
        for (int[] rows : data) {
            for (int data : rows) response.append(String.format("%3d", data));
            response.append("\n");
        }
        System.out.println(response);
    }
}