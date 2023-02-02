package Shoe;

public class Model {

    private int modelID;

    private String modelName;

    public Model() {
    }

    public Model(int modelID, String modelName) {
        this.modelID = modelID;
        this.modelName = modelName;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
