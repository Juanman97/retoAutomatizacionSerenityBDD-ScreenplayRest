package co.com.sofka.model.pokeapi.ingamelocations;

import java.util.List;

public class LocationModel {
    private List<Area> areas = null;
    private List<GameIndex> game_indices = null;
    private Integer id;
    private String name;
    private List<Name> names = null;
    private Region region;

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<GameIndex> getGame_indices() {
        return game_indices;
    }

    public void setGame_indices(List<GameIndex> game_indices) {
        this.game_indices = game_indices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
