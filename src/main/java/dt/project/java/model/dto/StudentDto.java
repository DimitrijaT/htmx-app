package dt.project.java.model.dto;

import lombok.Data;

@Data
public class StudentDto {
        public Long id;

        public String name;

        public String age;

        public StudentDto() {
        }

        public StudentDto(Long id, String name, String age) {
                this.id = id;
                this.name = name;
                this.age = age;
        }

}
