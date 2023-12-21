package dt.project.java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long id;

        public String name;

        public String age;

        public Student(String name, String age) {
                this.name = name;
                this.age = age;
        }

}