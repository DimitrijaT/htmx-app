package dt.project.java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Getter
public class Student {

        @Id
        public Long id;

        public String name;

        public String age;

}