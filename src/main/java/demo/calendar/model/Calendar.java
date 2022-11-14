package demo.calendar.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "calendar",uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20)", name = "id")
    private Long id;
    @Column(columnDefinition = "Time")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String starttime;//checked
    @Column(columnDefinition = "Time")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String endtime;//checked
    @Column(columnDefinition = "varchar(255)",name = "location")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String location;//checked
    @Column(columnDefinition = "varchar(255)",name = "owner")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String owner;//checked
    @Column(columnDefinition = "varchar(255)", name = "title")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String title;//checked
    @Column(columnDefinition = "tinytext")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String shortmsg;//checked
    @Column(columnDefinition = "text")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String fullmsg;//checked
    @Column(columnDefinition = "varchar(255)")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String receiver;//checked
    @Column(columnDefinition = "varchar(50)", name = "type")
    @NotEmpty(message = "Vui lòng nhập thông tin")
    private String type;//checked
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createddate;//checked
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Vui lòng nhập thông tin ngày tháng")
    private Date updateddate;//checked
}