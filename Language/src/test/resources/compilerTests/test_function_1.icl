//120:int
let
    factorial:(int)int = fun n:int ->
        if n == 0 then 1 else n * factorial(n - 1) end
    end
in
    println factorial(5)
end;;
