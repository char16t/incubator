pub enum Error {
    ErrorType1,
    ErrorType2,
    UnexpectedError,
}

impl std::convert::From<std::io::Error> for Error {
    fn from(_e: std::io::Error) -> Error {
        Error::UnexpectedError
    }
}

fn sub_function(arg: i32) -> Result<usize, Error> {
    Ok(42)
}

fn my_main() -> Result<(), Error> {
    let rr: usize = sub_function(24)?;
    Ok(())
}
