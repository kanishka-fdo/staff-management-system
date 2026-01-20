package com.staffmanagement.Department.service;

import com.staffmanagement.Department.model.Department;
import org.springframework.stereotype.Component;

/**
 * Factory class for creating Department objects.
 * This hides the complex object creation details from the client (Service/Controller).
 */
@Component
public class DepartmentFactory {

    /**
     * Defines an interface for creating objects (the Department entity).
     * @param name The required name of the department.
     * @param description The optional description of the department.
     * @return A newly created Department instance.
     * @throws RuntimeException if the description validation fails.
     */
    public Department createDepartment(String name, String description) {
        // Step 1: Basic validation (moved from controller logic)
        String trimmedName = name != null ? name.trim() : null;
        String trimmedDescription = description != null ? description.trim() : null;

        if (trimmedName == null || trimmedName.isEmpty()) {
            throw new RuntimeException("Department name cannot be empty");
        }

        // Step 2: Description word count validation (20 words max, as per the original frontend/controller logic)
        if (trimmedDescription != null && !trimmedDescription.isEmpty()) {
            String[] words = trimmedDescription.split("\\s+");
            if (words.length > 20) {
                throw new RuntimeException("Description cannot exceed 20 words");
            }
        }

        // Step 3: Object Creation Logic (the core Factory pattern responsibility)
        Department department = new Department();
        department.setDepartmentName(trimmedName);
        department.setDepartmentDescription(trimmedDescription);

        return department;
    }
}