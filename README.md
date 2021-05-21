# Task for Integral - Social - Networking

This branch has bene created to develop the task assigned for Integral Social - Networking task

# Build Steps

1. `git clone`
1. `cd katas`
1. `npm i`
1. `npm run test`

# About the Task

1. The said task has been built with `npm` package.
1. The ask was to create `Social Networking Kata` having the following Features / output
    - Feature: Publishing
        - Scenario: Alice publishes messages to her personal timeline.   
            - Given Alice has published "I love the weather today."
            - When Alice views her timeline
            - Then Alice sees:
                - "I love the weather today."
    - Feature: Timeline
        - Scenario: Alice views Bob's timeline.
            - Given Bob has published "Darn! We lost!"
            - And Bob has published "Good game though."
            - When Alice views Bob's timeline
            - Then Alice sees:
                - Good game though. (1 minute ago)
                - Darn! We lost! (2 minute ago)
      
    - Feature: Following
        - Scenario: Charlie can follow Alice and Bob, and he views an aggregated list of all timelines.
        - Given Alice has published "I love the weather today."
        - And Bob has published "Darn! We lost!"
        - And Bob has published "Good game though."
        - And Charlie has published "I'm in New York today! Anyone wants to have a coffee?"
        - When Charlie follows Alice
        - And Charlie follows Bob
        - And Charlie views his wall
        - Then Charlie sees:
            - Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)     
            - Bob - Good game though. (1 minute ago)     
            - Bob - Damn! We lost! (2 minutes ago)     
            - Alice - I love the weather today (5 minutes ago) 
1. To accomplish this, a total of 3 test cases has been written
1. Overall the folliwing activities happens for a social networking app:
    - User registers in the app
    - User publishes messages
    - One user's views others timelines
    - One follows other users
    - One views their respective walls.
1. All the above said actvities have been implemented in `kata.js`.
1. These methods have been utlized in `kata.test.js` in the three test cases written to accomplish the desired output.
1. The said task has been done by using only javascript and no UI / CSS, has been written.