**What & Why I Did**

1. Multi Module Architechture(Flat Feature Module): Why??  
   1. **Lightning-Fast Builds:** Gradle only recompiles the specific feature module you are editing. If you change a button in the `Reader` module, the `Library` module doesn't rebuild at all.  
   2. **Zero Spaghetti Code:** Because feature modules are physically forbidden from depending on one another, developers cannot accidentally entangle the logic of different screens.  
   3. **Team Parallelism:** Multiple developers can work on different features simultaneously without triggering massive Git merge conflicts.  
   4. **The "Goldilocks" Zone:** It provides 90% of the scalability benefits of DuckDuckGo's extreme architecture, but with significantly less Gradle boilerplate to maintain.  
        
        
2. Architechture Used in MVI over mvvm . Reason: Nn standard MVVM, you often end up with multiple MutableStateFlows or LiveData objects in your ViewModel. This can lead to fragmented state and hard-to-track bugs where part of the UI updates but another part doesn't. This is an evolution of MVVM that perfectly matches Compose. It enforces **Unidirectional Data Flow (UDF)**.  
   1. **State**: There is only *one* immutable `State` data class for the whole screen.  
   2. **Action**: User interactions are passed as explicit `Action` or `Event` objects (like `OnLoginClicked`).  
   3. This makes the UI completely predictable, easier to test, and much easier to debug.  
        
3. SOLID PRINCIPLES: The project strictly adheres to SOLID principles, ensuring each class has a Single Responsibility (SRP), classes are open for extension but closed for modification (OCP), and high-level modules depend on abstractions (DIP). We heavily utilize domain Value Objects and interface segregation.
4. Vertical Slicing: While we used a Flat Feature Module architecture for this take-home project, for a larger production app, we would transition to **Vertical Slicing** where each feature is a self-contained, end-to-end slice encompassing its own UI, domain, and data logic for maximum isolation and independent deployability.