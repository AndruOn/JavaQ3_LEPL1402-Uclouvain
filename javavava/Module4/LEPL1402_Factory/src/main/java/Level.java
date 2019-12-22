public class Level extends AbstractLevel {

    public Level(String input) {
        String[] splitInput = input.split("\n");
        this.size = splitInput.length;
        this.components = new LevelComponent[size][size];

        int i = 0;
        LevelComponent[] stage;
        for (String splitString : splitInput) {
            stage = new LevelComponent[size];
            for (int j = 0; j < size; j++) {
                stage[j] = getElement(splitString.charAt(j));
            }
            components[i] = stage;
            i++;
        }
    }

    public LevelComponent getElement(char c) {
        ElementFactory factory = ElementFactory.getInstance();
        return factory.getElement(c);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        LevelComponent[][] compo = getComponents();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                string.append(compo[i][j].draw());
            }
            string.append("\n");
        }
        return string.toString();
    }

    @Override
    public LevelComponent[][] getComponents() {
        return this.components;
    }


    @Override
    public int getSize() {
        return size * size;
    }
}