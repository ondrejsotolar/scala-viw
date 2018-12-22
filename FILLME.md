# Viw

## Extra Tests

Write which files contain your extra tests. If you did something extra or
impressive here let me know!
===================================================
Unit tests for mandatory part path: test/.../viw.mandatory/*
Unit tests for extra part path: test/.../viw.extra/*

My interpretation of the VIM commands is based on the VIM on Windows 
in MinGW environment. There are some differences to native 
Linux or Mac.

## Extensibility

Briefly explain which files you have to change to introduce a new type of
movement or a new type of action, e.g. move to the first whitespace character or
delete to the front. Hint, if this lists all the files in your project you
should reevaluate you implementation.
===================================================

The design is following:

viw/Commands/CommandRunner ------------------------
All key combinations (e.g. "x", "dd", etc.) are handled by their own object 
that extends the CommandRunner abstract class.
CommandRunner holds two lists: conditions and acttions. On calling the 
process(State) method, it checks all the conditions simultaneously,
and if succesful, then runs all the actions consecutively.

viw/conditions ------------------------------------
Basic starting preconditions for the command int the format of a 
method (State)=>Boolean. These can also be used inside the actions.

viw/actions/Action --------------------------------
Objects extending the Action trait implement the apply(State)=>State method. 
This contains the actual logic for a particular command.

viw/Viw -------------------------------------------
The "main" class matches the key pressed to a command. It also stores the last 
command and the last repeatable command. 

Conclusion: ---------------------------------------
I designed this structure this way because after implementing the single 
letter commands it's really easy to add the combined ones.
You just have to create a new Command object and add the corresponding 
actions to it's list. To add a new command you shouldn't have to change 
any of the existing commands. If the single command can't for whatever
reason be used in a combined one, just add a new one.

## Custom Extension

If you came up with your own extension, properly document it here. Explain its
intended behavior and showcase by example.
===================================================

Unfortunately, I had no time for this. My time tracker already shows 40 hours of work for this project.

## Feedback on the Project 

After working on this thing for such a long time you're allowed to vent in this
section. Tell me what you learned or what you would've liked to learn instead,
etc. This does not count toward your grades at all (positive nor negative).
===================================================

Negatives
1. Vague requirements: why not point to a VIM specification?
2. Secret evaluation criteria: secret set of unit tests. Why not make them public?
3. Many bugs in the initial code
4. Non-interesting topic: the implementation is 99% about checking index boundaries
5. Way too much of very similar work. 
6. Platform dependence: VIM is behaves differently on Linux,Win-MinGW,MacOS. Different line endings
are just one part of it.
7. Too much importance of the project: after studying CPL for the whole semester, I might not 
pass the course if my project is not good enough. And there's no chance for redoing it. 

Positives
1. Scala is nice
2. Easy setup in IDEA


