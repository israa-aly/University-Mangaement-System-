/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university;

/**
 *
 * @author DELL
 */
import DTO.CourseDTO;
import javafx.util.StringConverter;

public class CourseStringConverter extends StringConverter<CourseDTO> {

    @Override
    public String toString(CourseDTO course) {
        // Convert CourseDTO to a string for display
        return course.getName();
    }

    @Override
    public CourseDTO fromString(String string) {
        // Convert the displayed string back to a CourseDTO (if needed)
        // Note: This conversion is not necessary for ComboBox display
        return null;  // You can return a CourseDTO if needed
    }
}
