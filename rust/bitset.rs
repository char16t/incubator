/// bitwise operations

fn set_bit(n: usize, pos: usize) -> usize {
    n | (1 << pos)
}

fn unset_bit(n: usize, pos: usize) -> usize {
    n & !(1 << pos)
}

fn toggle_bit(n: usize, pos: usize) -> usize {
    n ^ (1 << pos)
}

fn is_bit_set(n: usize, pos: usize) -> bool {
    (n & (1 << pos)) != 0
}

fn is_power_of_two(n: usize) -> bool {
    (n & (n - 1)) == 0
}

/// Brian Kernighan's algorithm
fn count_set_bits(n: usize) -> usize {
    let mut count = 0;
    let mut num = n;
    while num != 0 {
        num &= num - 1;
        count += 1;
    }
    count
}

/// bitwise matrix

fn set_bit_in_matrix(matrix: usize, n: usize, m: usize, row: usize, col: usize) -> usize {
    set_bit(matrix, row * m + col)
}

fn unset_bit_in_matrix(matrix: usize, n: usize, m: usize, row: usize, col: usize) -> usize {
    unset_bit(matrix, row * m + col)
}

fn toggle_bit_in_matrix(matrix: usize, n: usize, m: usize, row: usize, col: usize) -> usize {
    toggle_bit(matrix, row * m + col)
}

fn is_bit_set_in_matrix(matrix: usize, n: usize, m: usize, row: usize, col: usize) -> bool {
    is_bit_set(matrix, row * m + col)
}

/// sets

fn union(a: usize, b: usize) -> usize {
    a ^ b
}

fn intersection(a: usize, b: usize) -> usize {
    a & b
}

fn symetric_difference(a: usize, b: usize) -> usize {
    a ^ b
}

fn difference(a: usize, b: usize) -> usize {
    a & !b
}

fn is_subset(a: usize, b: usize) -> bool {
    (a & b) == a
}

fn is_superset(a: usize, b: usize) -> bool {
    (a & b) == b
}
