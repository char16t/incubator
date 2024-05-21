fn take_int() -> usize {
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    input.trim().parse().unwrap()
}

fn take_2int() -> (usize, usize) {
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    let mut values: SplitWhitespace = input.trim().split_whitespace();
    let ax: usize = values.next().unwrap().parse().unwrap();
    let ay: usize = values.next().unwrap().parse().unwrap();
    (ax, ay)
}

fn take_3int() -> (usize, usize, usize) {
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read line");
    let mut values: SplitWhitespace = input.trim().split_whitespace();
    let ax: usize = values.next().unwrap().parse().unwrap();
    let ay: usize = values.next().unwrap().parse().unwrap();
    let az: usize = values.next().unwrap().parse().unwrap();
    (ax, ay, az)
}

fn take_vector() -> Vec<usize> {
    let mut input: String = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let arr: Vec<usize> = input.trim().split_whitespace().map(|x| x.parse().unwrap()).collect();
    return arr;
}

fn take_string() -> Vec<char> {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let vec:Vec<char> = input.trim().chars().collect();
    return vec;
}
