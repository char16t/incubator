pub struct DemoStruct {}

impl Default for DemoStruct {
    fn default() -> Self {
        todo!()
    }
}

impl std::fmt::Debug for DemoStruct {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        todo!()
    }
}

impl std::fmt::Display for DemoStruct {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        todo!()
    }
}

impl Clone for DemoStruct {
    fn clone(&self) -> Self {
        Self {}
    }
}
//impl Copy for DemoStruct {}

impl PartialEq for DemoStruct {
    fn eq(&self, other: &Self) -> bool {
        todo!()
    }
}
impl Eq for DemoStruct {}

impl PartialOrd for DemoStruct {
    fn partial_cmp(&self, other: &Self) -> Option<std::cmp::Ordering> {
        None
    }
}
impl Ord for DemoStruct {
    fn cmp(&self, other: &Self) -> std::cmp::Ordering {
        todo!()
    }
}

impl std::hash::Hash for DemoStruct {
    fn hash<H: std::hash::Hasher>(&self, state: &mut H) {}
}

impl Iterator for DemoStruct {
    type Item = usize;

    fn next(&mut self) -> Option<Self::Item> {
        todo!()
    }
}

/// Converts a byte to a DemoStruct.
impl std::convert::From<u8> for DemoStruct {
    fn from(orig: u8) -> DemoStruct {
        DemoStruct {}
    }
}

/// Converts a DemoStruct to a byte.
impl std::convert::Into<u8> for DemoStruct {
    fn into(self) -> u8 {
        0u8
    }
}

/// Overrides + operator
impl std::ops::Add for DemoStruct {
    type Output = DemoStruct;
    fn add(self, rhs: Self) -> Self::Output {
        todo!()
    }
}

/// Overrides - operator
impl std::ops::Sub for DemoStruct {
    type Output = DemoStruct;
    fn sub(self, rhs: Self) -> Self::Output {
        todo!()
    }
}

/// Overrides * operator
impl std::ops::Mul for DemoStruct {
    type Output = DemoStruct;
    fn mul(self, rhs: Self) -> Self::Output {
        todo!()
    }
}

/// Overrides * operator
impl std::ops::Div for DemoStruct {
    type Output = DemoStruct;
    fn div(self, rhs: Self) -> Self::Output {
        todo!()
    }
}

/// Overrides % operator
impl std::ops::Rem for DemoStruct {
    type Output = DemoStruct;
    fn rem(self, rhs: Self) -> Self::Output {
        todo!()
    }
}

/// Overrides unary - operator
impl std::ops::Neg for DemoStruct {
    type Output = DemoStruct;
    fn neg(self) -> Self::Output {
        DemoStruct {}
    }
}

/// Overrides += operator
impl std::ops::AddAssign for DemoStruct {
    fn add_assign(&mut self, rhs: Self) {
        todo!()
    }
}

/// Overrides -= operator
impl std::ops::SubAssign for DemoStruct {
    fn sub_assign(&mut self, rhs: Self) {
        todo!()
    }
}

/// Overrides *= operator
impl std::ops::MulAssign for DemoStruct {
    fn mul_assign(&mut self, rhs: Self) {
        todo!()
    }
}

/// Overrides /= operator
impl std::ops::DivAssign for DemoStruct {
    fn div_assign(&mut self, rhs: Self) {
        todo!()
    }
}

/// Overrides %= operator
impl std::ops::RemAssign for DemoStruct {
    fn rem_assign(&mut self, other: DemoStruct) {
        todo!()
    }
}

impl AsRef<i32> for DemoStruct {
    fn as_ref(&self) -> &i32 {
        todo!()
    }
}

impl AsMut<i32> for DemoStruct {
    fn as_mut(&mut self) -> &mut i32 {
        todo!()
    }
}

impl Drop for DemoStruct {
    fn drop(&mut self) {
        println!("MyStruct is being dropped");
    }
}

/// Overrides deref operator *
impl std::ops::Deref for DemoStruct {
    type Target = DemoStruct;
    fn deref(&self) -> &Self::Target {
        todo!()
    }
}

/// Overrides deref mut operator *
impl std::ops::DerefMut for DemoStruct {
    fn deref_mut(&mut self) -> &mut Self::Target {
        todo!()
    }
}

struct MyClosure<F: Fn(i32) -> i32> {
    func: F,
}

impl<F: Fn(i32) -> i32> MyClosure<F> {
    fn call(&self, arg: i32) -> i32 {
        (self.func)(arg)
    }
}
