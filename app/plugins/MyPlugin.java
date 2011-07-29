package plugins;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import play.Logger;
import play.Play;
import play.PlayPlugin;

public class MyPlugin extends PlayPlugin {

    public static final Map<String, Object> models = new HashMap<String, Object>();

    @Override
    public void onApplicationStart() {

        Logger.info("Application started");
        List<Class> entities = Play.classloader.getAnnotatedClasses(Entity.class);
        for (Class clazz : entities) {
            String tableName = clazz.getCanonicalName();
            Annotation classAnnotation = clazz.getAnnotation(Table.class);
            if (classAnnotation != null) {
                tableName = ((Table) classAnnotation).name();
            }
            System.out.println("* " + clazz.getName() + " (" + tableName + ")");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                boolean nullable = true;
                boolean unique = false;
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(Column.class)) {
                        // TODO
                    }
                }

                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    nullable = column.nullable();
                    unique = column.unique();
                }
                System.out.println("  - " + field.getName() + " (" + field.getType().getCanonicalName() + ")");
                System.out.println("      # unique: " + unique + ", nullable: " + nullable);
            }
        }
    }
}
