package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Geometries {
    private List<Intersectable> intrsc;
    public Geometries()
    {
        intrsc = new LinkedList<Intersectable> ();
    }

    public Geometries(Intersectable... geometries){
        intrsc = new LinkedList<Intersectable>(List.of(geometries));
    }

    public void add(Intersectable... geometries){
        for (int i = 0; i < geometries.length; i++){
            intrsc.add(geometries[i]);
        }
    }

    List<Point> filter(List<Point> result, List<Point> fi) {
        for (Point p : fi) {
            if(result.contains(p)) {
                if (fi.size() == 1)
                    return null;
                fi.remove(p);
            }
        }
        return fi;
    }

    List<Point> findIntersections(Ray ray){
        List<Point> result = null;
        for (Intersectable i : intrsc) {
            List<Point> fi = i.findIntsersections(ray);
            if (fi != null) {
                if (result == null)
                    result = new LinkedList<Point>(fi);
                else {
                    List<Point> l = this.filter(result,fi);
                    if(l != null)
                        result.addAll(l);
                }
            }
        }
        return result;
    }
}
