//! Build prefix sum array for given slice.
//! Usage example:
//! 
//!   let original_array = vec![1, 2, 3, 4, 5];
//!   let prefix_sum_array = build_prefix_sum_array(&original_array);
//! 
fn build_prefix_sum_array(arr: &[i32]) -> Vec<i32> {
    let mut prefix_sum = vec![0; arr.len() + 1];
    for i in 1..prefix_sum.len() {
        prefix_sum[i] = prefix_sum[i - 1] + arr[i - 1];
    }
    prefix_sum
}

//! Get sum on range using prefix sum array.
//! Usage:
//! 
//!   let original_array = vec![1, 2, 3, 4, 5];
//!   let prefix_sum_array = build_prefix_sum_array(&original_array);
//!   let sum_on_range = range_sum(&prefix_sum_array, 1, 3);
//! 
fn range_sum(prefix_sum: &[i32], l: usize, r: usize) -> i32 {
    if l == 0 {
        prefix_sum[r + 1]
    } else {
        prefix_sum[r + 1] - prefix_sum[l]
    }
}
