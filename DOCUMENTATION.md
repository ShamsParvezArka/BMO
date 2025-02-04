<h1 align="center">
	BMO Documentation
</h1>

> Go back to [main page](https://github.com/ShamsParvezArka/bmo)

## How to execute a program
```powershell
java -jar bmo.jar [script.bmo]
```
There's a `main.bmo` file already provided in the `build` directory. To execute this scirpt simply do
```powershell
java -jar bmo.jar .\main.bmo
```
> n.b. we highly recommend to use the windows powershell as a shell emulator.

## Printing "Hello, world!"
```
print "Hello, world!"
```

## Comments
```
// hi, this is a comment
```

## Variable Declaration
```
var name = "BMO";
var version = "0.0.1";
var is_cool = true;
var magic_number = 69;
```

## Scopes
```
var a = "outer";
{
	var a = inner;
	print a;
}
print a;
``` 

## Control Flow
```
var is_cool = true;
if (is_cool) 
{
	print "BMO is soooo good";
} 
else 
{
	print "BMO is dog shit!";
}
```

## Loops
```
// normal for loop
for (var i = 1; i < 5; i = i + 1)
{
	print i;
}

// normal while loop
var i = 1;
while (i < 5)
{
	print i;
	i = i + 1;
}
```

## Functions
```
// fibonacci function
fun fib(n) {
	if (n <= 1) 
		return n;

	return fib(n - 2) + fib(n - 1);
}

for (var i = 0; i < 20; i = i + 1) {
	print fib(i);
}
```
