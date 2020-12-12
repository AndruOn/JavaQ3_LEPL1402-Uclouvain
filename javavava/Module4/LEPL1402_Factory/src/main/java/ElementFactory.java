public class ElementFactory extends Factory {

    private static final ElementFactory instance = new ElementFactory();

    private ElementFactory(){};

    public static ElementFactory getInstance() {
        return instance;
    }

    @Override
    public LevelComponent getElement(char c) {

        switch (c){
            case '#':
                return new Wall();
            case 'D':
                return new Door();
            case 'K':
                return new Key();
            case '-':
                return new Floor();
            default:
                throw new IllegalArgumentException();
        }
    }
}


