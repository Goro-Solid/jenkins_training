Invoke-Pester -OutputFile "$ENV:WORKSPACE\003_ci\powershell\Test.xml" -OutputFormat NUnitXml -Path "$ENV:WORKSPACE\003_ci\powershell\app.Tests.ps1"
