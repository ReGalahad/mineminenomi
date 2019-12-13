# Step 1, variable setup, change the build type, used for patreon access logic AND loading the .env variables

$buildVersion = "0.5.1"
$location = Get-Location
$rootLocation = Resolve-Path (Join-Path $location '..')
$buildsLocation = "$rootLocation\build\libs"
$toolsLocation = "$rootLocation\tools"
$location = "$rootLocation\src\main\java\xyz\pixelatedw\mineminenomi\values\ModValuesEnv.java"

Set-PsEnv

(Get-Content $location) -Replace 'BUILD_MODE = \"(.*?)\"', 'BUILD_MODE = "DEV"' | Set-Content $location

Write-Host -ForegroundColor Green -BackgroundColor Black "Switch BUILD_MODE to DEV"

# Step 2, change the branch, build the actual .jar file using gradle and then change back to our previous branch

Set-Location $rootLocation
$workingBranch = git rev-parse --abbrev-ref HEAD
git checkout $buildVersion/update
.\gradlew build
git checkout $workingBranch

# Step 3, upload the new build on the server

pscp -r -p -P $env:SERVER_PORT -l $env:SERVER_USER -pw $env:SERVER_PASSWORD "D:\Programming\Forge Projects\Mine Mine no Mi - 1.14.4\build\libs\*.jar" "$env:SERVER_USER@$env:SERVER_DN$env:SERVER_SAVE_PATH/dev/"

# Step 4, clean the folder where the build was created locally

Set-Location $buildsLocation
Remove-Item * -Include "*.jar"

# Step 5, come back to the tools folder

Set-Location $toolsLocation
