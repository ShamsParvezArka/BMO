$argument = $args[0]
$compiler = "javac"
$output = "-d .\class"
$source_file = ".\source\*"
$flags = "-Xlint --target 23"

function Run-Prog 
{
    Push-Location .\bin 
    java Bmo .\main.bmo
    Pop-Location
}

function Compress-Prog
{
    Push-Location build\
    jar cfm bmo.jar ..\META-INF\MANIFEST.MF -C ..\class\ .
    Pop-Location 
}

if ($argument -eq "run") 
{
    Run-Prog
}
elseif ($argument -eq "compress")
{
    Compress-Prog
}
else 
{
    "$compiler $output $source_file $flags" | Invoke-Expression
}