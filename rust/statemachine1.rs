use std::collections::HashMap;

struct TransitionConfig {
    target: String
}
impl TransitionConfig {
    fn new(target: &str) -> Self {
        Self {
            target: target.to_string()
        }
    }
}

struct StateConfig {
    transitions: HashMap<String, TransitionConfig>
}
impl StateConfig {
    fn new() -> Self {
        Self {
            transitions: HashMap::new()
        }
    }
    fn permit(&mut self, event: &str, target: &str) -> &mut Self {
        if let Some(transition) = self.transitions.get_mut(event) {
            transition.target = target.to_string();
        } else {
            self.transitions.insert(event.to_string(), TransitionConfig::new(target));
        }
        self
    }
}

struct StateMachineConfig {
    states: HashMap<String, StateConfig>
}
impl StateMachineConfig {
    fn new() -> Self {
        Self {
            states: HashMap::new()
        }
    }
    fn configure(&mut self, state: &str) -> &mut StateConfig {
        self.states
            .entry(state.to_string())
            .or_insert(StateConfig::new())
    }
}

struct StateMachine {
    current: String,
    config: StateMachineConfig
}
impl StateMachine {
    fn new(state: &str, config: StateMachineConfig) -> Self {
        Self {
            current: state.to_string(),
            config: config,
        }
    }
    fn transition(&mut self, event: &str) {
        if let Some(state_config) = self.config.states.get(&self.current) {
            if let Some(transition) = state_config.transitions.get(event) {
                self.current = transition.target.clone();
            }
        }
    }
}

fn main() {
    let mut conf = StateMachineConfig::new();
    conf.configure("on").permit("switch", "off");
    conf.configure("off").permit("switch", "on");

    let mut machine = StateMachine::new("off", conf);
    println!("Current state: {}", machine.current);
    machine.transition("switch");
    println!("Current state: {}", machine.current);
    machine.transition("switch");
    println!("Current state: {}", machine.current);
}
