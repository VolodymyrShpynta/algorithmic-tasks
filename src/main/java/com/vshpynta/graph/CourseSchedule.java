package com.vshpynta.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//207. Course Schedule: https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }

        var prerequisitesAdjacencyLists = buildAdjacencyLists(prerequisites);
        var verifiedCourses = new HashSet<Integer>();  //use to detect loops in course prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (canNotFinish(i, prerequisitesAdjacencyLists, verifiedCourses)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canNotFinish(int course,
                                        Map<Integer, List<Integer>> prerequisitesAdjacencyLists,
                                        Set<Integer> verifiedCourses) {
        if (verifiedCourses.contains(course)) { //loop detected. Can't finish course due to loop in the prerequisites
            return true;
        }

        var prerequisiteCourses = prerequisitesAdjacencyLists.get(course);
        if (prerequisiteCourses != null) {
            verifiedCourses.add(course);
            for (Integer prerequisiteCourse : prerequisiteCourses) {
                //use recursion (DFS) to verify all prerequisite courses in the prerequisites graph branch
                if (canNotFinish(prerequisiteCourse, prerequisitesAdjacencyLists, verifiedCourses)) {
                    return true;
                }
            }
            //remove in order to have ability run verification for other course (which can have dependency on this course):
            verifiedCourses.remove(course);
        }

        prerequisitesAdjacencyLists.remove(course); //remove course from prerequisites to avoid double verification
        return false;
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
        System.out.println(canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println(canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}, {2, 0}}));
    }
}
