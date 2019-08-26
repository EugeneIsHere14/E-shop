package com.epam.preproduction.koshevyi.entity;

import java.util.List;

/**
 * Entity class which represents
 * url-patterns and roles for them.
 */
public class PageAccess {

    private List<Constraint> constraints;

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public static class Constraint {

        private List<Integer> roles;

        private List<String> urlPatterns;

        public List<String> getUrlPatterns() {
            return urlPatterns;
        }

        public List<Integer> getRoles() {
            return roles;
        }

        public Constraint(List<Integer> roles, List<String> urlPatterns) {
            this.roles = roles;
            this.urlPatterns = urlPatterns;
        }

        @Override
        public String toString() {
            return "Constraint{" +
                    "roles=" + roles +
                    ", urlPatterns=" + urlPatterns +
                    '}';
        }
    }
}