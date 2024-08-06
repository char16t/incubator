macro_rules! my_attribute {
    ($name:ident) => {
        struct $name {
            value: i32,
        }

        impl $name {
            fn new(value: i32) -> Self {
                $name { value }
            }

            fn myval(&self) {
                println!("Value: {}", self.value);
            }
        }
    };
}

struct DemoStruct;

fn main() {
    // Use macro as attribute
    my_attribute!(DemoStruct);
    
    let ds: DemoStruct = DemoStruct::new(12);
    ds.myval();
  
    println!("Hello, world!");
}
