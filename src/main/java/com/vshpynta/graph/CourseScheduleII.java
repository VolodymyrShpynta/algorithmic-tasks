package com.vshpynta.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//210. Course Schedule II: https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[0];
        }

        var prerequisitesAdjacencyLists = buildAdjacencyLists(prerequisites);
        var coursesOrder = new ArrayList<Integer>(); //the result list of courses order
        var cycleCourses = new HashSet<Integer>();  //use to detect cycles in course prerequisites
        var visitedCourses = new HashSet<Integer>();  //use to detect course which already had been processed
        for (int i = 0; i < numCourses; i++) {
            if (!addToOrder(i, prerequisitesAdjacencyLists, cycleCourses, visitedCourses, coursesOrder)) {
                return new int[0];
            }
        }
        return coursesOrder.stream().mapToInt(Integer::intValue).toArray();
    }

    private static boolean addToOrder(int course,
                                      Map<Integer, List<Integer>> prerequisitesAdjacencyLists,
                                      Set<Integer> cycleCourses,
                                      Set<Integer> visitedCourses,
                                      List<Integer> coursesOrder) {
        if (cycleCourses.contains(course)) { //loop detected. Can't finish course due to loop in the prerequisites
            return false;
        }
        if (visitedCourses.contains(course)) { //course is already added to the courses order.
            return true;
        }

        var prerequisiteCourses = prerequisitesAdjacencyLists.get(course);
        if (prerequisiteCourses != null) {
            cycleCourses.add(course);
            for (Integer prerequisiteCourse : prerequisiteCourses) {
                //use recursion (DFS) to verify all prerequisite courses in the prerequisites graph branch:
                if (!addToOrder(prerequisiteCourse, prerequisitesAdjacencyLists, cycleCourses, visitedCourses, coursesOrder)) {
                    return false;
                }
            }
            //remove in order to have ability run verification for other course (which can have dependency on this course):
            cycleCourses.remove(course);
        }

        visitedCourses.add(course);
        coursesOrder.add(course);
        return true;
    }

    private static Map<Integer, List<Integer>> buildAdjacencyLists(int[][] prerequisites) {
        var adjacencyLists = new HashMap<Integer, List<Integer>>();
        for (int[] prerequisite : prerequisites) {
            adjacencyLists.compute(prerequisite[0], (vertex, adjacencyList) -> {
                if (adjacencyList == null) {
                    adjacencyList = new ArrayList<>();
                }
                adjacencyList.add(prerequisite[1]);
                return adjacencyList;
            });
        }
        return adjacencyLists;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(3, new int[][]{{0, 1}, {0, 2}, {1, 2}})));
        System.out.println(Arrays.toString(findOrder(3, new int[][]{{0, 1}, {0, 2}, {1, 2}, {2, 0}})));
    }
}
