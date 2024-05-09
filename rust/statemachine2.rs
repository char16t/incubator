use std::fmt;

trait State {
    fn switch(&self) -> Box<dyn State>;
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result;
}
impl fmt::Display for dyn State {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        self.fmt(f)
    }
}

struct Off {}
impl State for Off {
    fn switch(&self) -> Box<dyn State> {
        Box::new(On {})
    }
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "(off)")
    }
}

struct On {}
impl State for On {
    fn switch(&self) -> Box<dyn State> {
        Box::new(Off {})
    }
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "(on)")
    }
}

fn main() {
    let m: Box<dyn State> = Box::new(Off {});
    println!("{}", m);
    let m: Box<dyn State> = m.switch();
    println!("{}", m);
    let m: Box<dyn State> = m.switch();
    println!("{}", m);
}
