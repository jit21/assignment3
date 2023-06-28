// Given an integer array nums of length n and an integer target, find three integers
// in nums such that the sum is closest to the target.
// Return the sum of the three integers.
import java.util.Arrays;

public class assignment3 {
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2]; // Initialize closestSum with the sum of the first three elements

        Arrays.sort(nums); // Sort the array in ascending order

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                if (currentSum == target) {
                    return currentSum; // Return early if the sum is equal to the target
                }

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum; // Update closestSum if the current sum is closer to the target
                }

                if (currentSum < target) {
                    left++; // Move the left pointer to increase the sum
                } else {
                    right--; // Move the right pointer to decrease the sum
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = { -1, 2, 1, -4 };
        int target = 1;
        int closestSum = threeSumClosest(nums, target);
        System.out.println("Closest sum: " + closestSum);
    }
}
// Given an array nums of n integers, return an array of all the unique quadruplets
// [nums[a], nums[b], nums[c], nums[d]] such that:
//            ● 0 <= a, b, c, d < n
//            ● a, b, c, and d are distinct.
//            ● nums[a] + nums[b] + nums[c] + nums[d] == target

// You may return the answer in any order.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class assignment3{
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicates to avoid duplicate quadruplets
            }

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // Skip duplicates to avoid duplicate quadruplets
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates to avoid duplicate quadruplets
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Move the left pointer to increase the sum
                    } else {
                        right--; // Move the right pointer to decrease the sum
                    }
                }
            }
        }

        return quadruplets;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        int target = 0;
        List<List<Integer>> quadruplets = fourSum(nums, target);

        System.out.println("Quadruplets:");
        for (List<Integer> quadruplet : quadruplets) {
            System.out.println(quadruplet);
        }
    }
}

// 💡 A permutation of an array of integers is an arrangement of its members into a
// sequence or linear order.

// For example, for arr = [1,2,3], the following are all the permutations of arr:
// [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].

// The next permutation of an array of integers is the next lexicographically greater
// permutation of its integer. More formally, if all the permutations of the array are
// sorted in one container according to their lexicographical order, then the next
// permutation of that array is the permutation that follows it in the sorted container.

// If such an arrangement is not possible, the array must be rearranged as the
// lowest possible order (i.e., sorted in ascending order).

// ● For example, the next permutation of arr = [1,2,3] is [1,3,2].
// ● Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
// ● While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not
// have a lexicographical larger rearrangement.

// Given an array of integers nums, find the next permutation of nums.
// The replacement must be in place and use only constant extra memory.
import java.util.Arrays;

public class assignment3 {
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Find the first decreasing element from the right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;

            // Find the first element greater than nums[i] from the right
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }

            // Swap nums[i] and nums[j]
            swap(nums, i, j);
        }

        // Reverse the sub-array from i+1 to the end
        reverse(nums, i + 1, n - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Original Array: " + Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println("Next Permutation: " + Arrays.toString(nums));
    }
}
// Given a sorted array of distinct integers and a target value, return the index if the
// target is found. If not, return the index where it would be if it were inserted in
// order.

// You must write an algorithm with O(log n) runtime complexity.
public class assignment3 {
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int index = searchInsert(nums, target);
        System.out.println("Target found at index: " + index);
    }
}
// You are given a large integer represented as an integer array digits, where each
// digits[i] is the ith digit of the integer. The digits are ordered from most significant
// to least significant in left-to-right order. The large integer does not contain any
// leading 0's.

// Increment the large integer by one and return the resulting array of digits.
import java.util.Arrays;

public class assignment3 {
    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        // Start from the least significant digit
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++; // Increment the digit
                return digits;
            } else {
                digits[i] = 0; // Set the digit to 0 and continue to the next digit
            }
        }

        // If all digits were 9, create a new array with an additional leading 1
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1;

        return newDigits;
    }

    public static void main(String[] args) {
        int[] digits = {1,2,3};
        System.out.println("Original Array: " + Arrays.toString(digits));
        int[] result = plusOne(digits);
        System.out.println("Resulting Array: " + Arrays.toString(result));
    }
}
// Given a non-empty array of integers nums, every element appears twice except
// for one. Find that single one.

// You must implement a solution with a linear runtime complexity and use only
// constant extra space.
public class assignment3 {
    public static int singleNumber(int[] nums) {
        int result = 0;

        // XOR all elements in the array
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        int single = singleNumber(nums);
        System.out.println("Single Number: " + single);
    }
}
// You are given an inclusive range [lower, upper] and a sorted unique integer array
// nums, where all elements are within the inclusive range.

// A number x is considered missing if x is in the range [lower, upper] and x is not in
// nums.

// Return the shortest sorted list of ranges that exactly covers all the missing
// numbers. That is, no element of nums is included in any of the ranges, and each
// missing number is covered by one of the ranges.
import java.util.ArrayList;
import java.util.List;

public class assignment3 {
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> missingRanges = new ArrayList<>();

        // Helper function to add missing range to the result list
        addRange(missingRanges, (long) lower - 1, (long) nums[0] - 1);

        for (int i = 1; i < nums.length; i++) {
            addRange(missingRanges, (long) nums[i - 1], (long) nums[i]);
        }

        // Add missing range from the last number in nums to upper
        addRange(missingRanges, (long) nums[nums.length - 1], (long) upper + 1);

        return missingRanges;
    }

    private static void addRange(List<String> missingRanges, long start, long end) {
        if (start == end) {
            return; // No missing range
        } else if (start + 1 == end) {
            missingRanges.add(String.valueOf(start)); // Single missing number
        } else {
            missingRanges.add(start + "->" + (end - 1)); // Range of missing numbers
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;
        List<String> missingRanges = findMissingRanges(nums, lower, upper);

        System.out.println("Missing Ranges:");
        for (String range : missingRanges) {
            System.out.println(range);
        }
    }
}
// Given an array of meeting time intervals where intervals[i] = [starti, endi],
// determine if a person could attend all meetings.
import java.util.Arrays;

public class assignment3 {
    public static boolean canAttendMeetings(int[][] intervals) {
        // Sort the intervals based on the start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Check if there is any overlap between consecutive meetings
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false; // Overlap found, cannot attend all meetings
            }
        }

        return true; // No overlap, can attend all meetings
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        boolean canAttendAllMeetings = canAttendMeetings(intervals);
        System.out.println("Can attend all meetings: " + canAttendAllMeetings);
    }
}








