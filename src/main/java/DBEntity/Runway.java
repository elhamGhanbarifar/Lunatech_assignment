package main.java.DBEntity;

public class Runway {
    private Long id;
    private Long airport_ref;
    private String surface;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getAirport_ref() {
        return airport_ref;
    }

    public void setAirport_ref(Long airport_ref) {
        this.airport_ref = airport_ref;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        return "Runway [" + id + "_" + surface + "_" + airport_ref + "]";
    }
}