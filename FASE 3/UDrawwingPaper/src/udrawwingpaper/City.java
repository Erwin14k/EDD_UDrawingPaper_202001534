/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawwingpaper;

/**
 *
 * @author Erwin14k
 */
public class City {
    private int id;
    private String department;
    private String name;
    private String office;
    private RoutesList routesList;
    public City(int id, String department,String name, String office,RoutesList routesList){
        this.id=id;
        this.department=department;
        this.name=name;
        this.office=office;
        this.routesList=routesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public RoutesList getRoutesList() {
        return routesList;
    }

    public void setRoutesList(RoutesList routesList) {
        this.routesList = routesList;
    }
    
    
}
