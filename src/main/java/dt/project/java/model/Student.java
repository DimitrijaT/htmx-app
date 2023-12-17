package dt.project.java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {

        @Id
        public Long id;

        public String name;

        public String age;

}